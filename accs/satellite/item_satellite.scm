	� G   ,�       �V[O�8~.��*�*�!�Ҳ�J+ua.,��"�o�I��jbG�����Ks|ktv�e�H���?;��YI
e�Vg$�%If�������Т�B�%�\�0�����pFx�NWm�	��Rb� `�$��ϔ¹�u
:&�����������o4FG�#Ooo��W��&<���ww:9�����.O��H=}P�TD�ъ���Kq^i��U�#'�-p�!7I���`�0"��6@�{��$(ywqW��yN%iVa�Z��^�F~� (揘ʮ��kyɤ��۪t���PP�Pe�r��q4�g-G����tJ�c�� ϻ;�7��IF���J������1���G��Z�&��QP�!��ἶD)Q��D�R�L&���U��?���������z�~2n��'�>7�G��/	�,!ʨ�kS�<!���l�o��(�l��u���i	t�x�!�X�#
�A��xc{bm�q�W�%���*y�u�؆OUx�������;��Iߑz�����dDNE�`�����3T���n��&��{�X[@׎*�o`��E(x�^��M�®��x���cL�5����X�hr�.�]Ѓ1:>��\{3b�x#���t�F��k��7z�w�1r*g-�ޛ�=E�zd9S@���RxNX&^������\}��98�Z�'��!�]JW*t8���Q#y��bh�m��y�a�!�p0��u\f��m��_5�N���\�����[蝺u�܀��3{�#Uԧ�?��m� /`i��T���΃�ӡ��z���G��AN�#������P<�-]��'dS)q�D0U1m̋�A0�ڂ����B�ႶO�قs9WCĎ��W�"T��$���^PY�%���jNpס$U;ڏ̻N�v];javӥ�,�Xd/̍Z(PZ��T$�/H�l��32���&���%�2�������jn��lEhk:_�����������٭f�����;��O�����f����$�� ��n#v�/i��������oX@��	   }  Lga2/stage/AmpedScript; e PROCESS_MAX 
ATTACK_MAX start process approachfloatconstant broken lockItemScript (ZZ)V
 f m n 
setVisible (Z)V
 f p q
 H 0 3 setShadowState
 d t 3 clearGenerate
 d v 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d x y setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d { |D /accs/satellite/satellite.scm�            ?�  ?�  ?�      B�       	item_wait tempInt (I)Ljava/lang/Integer;
 d � � temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2���  setJudgmentApproachFloatConstant (FFF)V
 f � �A   >��� clearJudgmentKind
 f � 1 getArgv
 f � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � � ()F
 f � �C4  C�  �4  	 � R  length
 � � � (FF)F
 � � � min
 � � �B4  �� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate (Lkotori/geom/Quaternion;I)V
 f � � 	playSound
 d � 3 (II)V
 d � � 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � ��� ga2.stage.atk.HomingAttackD� �D /accs/satellite/Shoot_item.scm�            ?�  ?�  ?�              � hit_ea  d�   �    ��    a� d��  Lga2/stage/atk/HomingAttack; � range	 � �  fPI	 � �  attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � shot
 f m q fadeout kill
 f � 1 
stopScript
 f � 1 f g  h  - 1  � ��  i 1  O*� oW*� rW*2� sW*� uW*� wW**~� z� }W*� �W*� oW<� *� W�W� ���  j 1  q*� �L+� �+� �=|�~>~6� � 
�� *� W�W*x�� �W*d� sW*�b�� �W*X� sW*� �W*d� sW� � *� W�  k 1 a*� �L+� �=|�~>~6�W*x�� �W*� �:*� �:� �W� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8		��� 	�f8	� 	��� 
	�b8	� �8
� �� �8
� �� �� �Zd�8�� #bj8bj�� ��nj8b8�:� �:	� �W*2� �W*2� sW* ϶ �W* �� �W*� �W�:� �n� �*�� �W*� �W*� �ڶ �W*X� sW*� W�  l 1  1*� �W*� �W*� �ܶ �W* Ѷ �W*�� sW*� �W*� �W� X�]                        �  
	� G   ��       �VmO�H�N��m�N61�		W)��C�r���g��b�Z�5$-��7��J5�3�όw�7�,%a�����$���McA9���ݡy��D.���K�_�)��68Y6�:�-�9�I��v>U
g�)�4�DW�?ξL�w�'���<����]Z��-�9�f�^�a '8+	�8�!p-�����ie<^\H���=)* ��A�UyKKz�O;P`���DN>����%�z�!��J
ӐR� �{�����⁒�Q��qyX�eT��-,�=D��ދ��(������S��u�Y\0遴��,�|�)��X��%�£�hHO{C��hJZ��1�"74�mսȔ][5:�ھ�֖{'tb��H�n������")��@�i8���G#Ve����`�25FA��<��P�B��� ��Yeۢt ��x<�}�;��Qb�)l4���%6�gi+D���������@کP6��%h�B�	i���}3t�h3uW�4'LN
���2���h@1���?��[[=�����������жҞ��Vߛn-��$*�e����fmi�����f���f�uKb8����7%r"���/����|eD�b�����|e��Qq��	&߱���W���~���7�vN�|��1�W`�����P�	4�~�|�`���#+�ތ�8�H7%7��Q��Zh�^��ǎ���AC��*�bOQ�Z��Ek���ʹ��C����˟Z5'Q���82#a�%t�B��}-֒,(���)��t��2�?4���kv��ˎ3��+8�S�#����7p����gg�ٯ�6RE}�����&	��v��Mm��5�|i^M5q��W�q����m���IǍ��Y����DJ/lrUL�����70t�Szf��;o:�\ީ�f�^�KG*ck�[k��T�q.��vGpۡ$Q�E�N�fU�6v͡K��Xc�Qd���*�@��4�R��Pd<'�)���� ������jf��aF1��@��NB��ޭ�( mM���F3x}g��P_Z�>o�s����#���Q_����)�G��e�ܢ0��{�{W�-w}��������=����.W	�^����I�:g�M�e&R)ya=�-�?r���+   }  Lga2/stage/AmpedScript; e PROCESS_MAX 
ATTACK_MAX broken start process (Z)V approachfloatconstant lockItemScript (ZZ)V
 f n o 
setVisible
 f q l
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
 f n l fadeout kill
 f � 1 
stopScript
 f � 1 f g  h  i  - 1  � ���  j 1  m*� pW*� rW*2� sW*� uW*� wW**~� z� }W*� �W*� pW<� *� W�W���* ȶ sW<� *� W�W� ���  k l  ���*� �M,� �,� �>|�~6~6Й � � 
�� *� W�W*x�� �W*d� sW*�b�� �W*X� sW*� �W*d� sWЙ � � *� W�  m 1 �*� �L+� �=|�~>~6�Й 	�W*x�� �W*� �:*� �:� �W� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8		��� 	�f8	� 	��� 
	�b8	� �8
� �� �8
� �� �� �Zd�8�� #bj8bj�� ��nj8b8�:� �:	� �W*2� �W*2� sW* ϶ �W* �� �W*� �W�:� �n� �*�� �W*� �W*� �ڶ �W*X� sW�� *� W� 7	� �W*2� �W*� sW6� *� W�W� ���  i 1  1*� �W*� �W*� �ܶ �W* Ѷ �W*�� sW*� �W*� �W� X�\�                        �  
	� G  ��       �U�n�F=�Wl���aV%q��q['Mjz�˕D���˕#�P�-|�!H���)��-zizP�_*�m���KRb_"9o�͛����'��a��'��N�I��.�Q�Z�%��j�jD�[W�k�H(!#RE�Z��5F.m��H�-U������+;������B�#����iv*���T�=�3��Q��-�ʹ�i(����׶,�Ӯ�!�}�T1� xʥ�D[�{�c�����LɈ�*����q�g�sLZ���_��l��v�IA��A,�{ �Jɴ���ڿ�-_����_.�}o[O!��;I�:Ֆ�S�3<y�"��dZ ����O��1N����c��/mˤDŚ�L�6b"� F�E.�]"g��Q�FZU��*��J���룵l�1��r�'�{�l�1����!�������)��N$1����H�Ӂb�CS���w�-h���(Uq� �ȁ�4�-�vc�7E�o����k��b�#S}.' `����{��ڴ儰X�ɂ��������Ż�����W%�����������W��/�7����y�_W�ɓ�׆5��d�xR(���/�Z;����:�u�̐���4���F�k%ړ"�)�{0�Q�(��o���TDv�f��k
D9�1����T�Đ�涞����P�u��� ��������t;�]���tǞ�r���2���� ��|�oV�����v��.\��:��7p?-O�C[ݡ�] �5�ȃ�sn[0�� ����1����&�3�F]?#��,p����)M����p�(jĆ��%�\�C������ý�`�]�k���ks�6���a0�6�mؼ�6<�b�S�-s*����5M,��S�]|�VlDD}\m�#m�q f=�����o���6��Q�&G�DV�ki��%�*   d  Lga2/stage/AmpedScript; e Lkotori/gfx/KGLScriptModel; ele_ball start approachfloat broken
 H 0 3 setShadowState
 d m 3 	playSound
 d o 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d q rD /efx/electric/ele_ball3.scm�            ?�  ?�  ?�               start2 setJudgmentApproachFloat (F)V
 f v wA�   getArgv ()Ljava/lang/Object;
 f z { getPosition ()Lkotori/geom/Position;
 f } ~  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � cos (F)F
 H � � sin
 H � �	 � Q  Lkotori/geom/Position; � dir	 � � 	 � S 	 � R  (FFF)Lkotori/geom/Vector3D;
 � + � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � { getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d q �D "/accs/thunder_rod/tr_Fx_attack.scm�            ?fff?fff>�33             attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� �D #/accs/thunder_rod/tr_Fx_attack2.scm�            ?��?��?��            � hit_ee  d� ,    N  �       ,�>�         (IIFF)V
 d o �?���?@   clearJudgmentKind
 f � 1 lockItemScript (Z)V
 f � � g startScript (Ljava/lang/String;)Z
 � � � 
start2_end kill
 f � 1 
stopScript
 f � 1 f h g i 1  /*d� lW*� nW* ܶ pW*tu� s� * ȶ lW*y� xW�  j 1	  �*� |L*� M+,� �W+� �,� �� �j+� �,� �� �jfF+� �,� �� �j+� �,� �� �jb8+� �8+%� �W+� �� �� ��8+� �� �� �� �Zd�8�:� �:� �W*� �W*�� �W*��� �W*� �W*��� �W*�� lW*y� xW�  k 1  ,*� �W*� �W� � � ö �W*� lW*� �W*� �W� �@�                        �  